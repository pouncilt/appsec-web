/**
 * Created by tonte on 6/9/15.
 */
myApp.controller('questionnaireController', ['$scope', '$state', function ($scope, $state) {
    function containsWebAppInfoBlocks(currentApplication) {
        return function(application) {
            var containsWebAppInfo = false;
            if(Object.isDefined(currentWebApplicationUIObject) && Object.isNumeric(currentWebApplicationUIObject.guid)) {
                if (application instanceof WebAppInfo) {
                    if (application.guid === currentWebApplicationUIObject.guid){
                        containsWebAppInfo = true;
                    }
                }
            } else {
                if (application instanceof WebAppInfo) {
                    containsWebAppInfo = true;
                }
            }

            return containsWebAppInfo;
        }
    }

    function containsCredentialsWithGUID(credentialGUID, credentialIndexInfo) {
        return function(credential, credentialIndex, credentialArray) {
            if(credential.guid === credentialGUID) {
                credentialIndexInfo.index = credentialIndex;
                credentialIndexInfo.credential = credential;
                return true;
            }
        }
    }

    function containsIpAddress(ipAddress, ipAddressIndexInfo) {
        return function(existingIpAddress, ipAddressIndex, ipAddressArray) {
            if(existingIpAddress === ipAddress) {
                ipAddressIndexInfo.index = ipAddressIndex;
                ipAddressIndexInfo.ipAddress = existingIpAddress;
                return true;
            }
        }
    }

    $scope.questionnaireInfoUIObject = new QuestionnaireInfo().toUIObject();
    $scope.credentialFormElement = {username: null, password: null};
    $scope.appHostedLocationFormElement = null;
    $scope.questionnaireForm = {
        applicationTypeFormElement: null,
        appHostedLocationFormElement: null,
        loadBalancerIpAddressFormElement: null
    };
    $scope.loadBalancerIpAddressFormElement = null;
    $scope.currentWebApplicationUIObject = null;
    $scope.currentWafApplicationUIObject = null;
    $scope.currentMobileApplicationUIObject = null;
    $scope.disableEngagmentData = true;


    /*$scope.$watchGroup(['questionnaireInfoUIObject.sdlcPhase', 'questionnaireInfoUIObject.appDevOrigin'], function (newValues, oldValues) {
        var newSdlcPhase  = newValues[0];
        var newAppDevOrigin = newValues[1];
        var oldSdlcPhase  = oldValues[0];
        var oldAppDevOrigin = oldValues[1];

        if(newSdlcPhase === oldSdlcPhase && newAppDevOrigin === oldAppDevOrigin){
            return;
        } else {
            if ((newSdlcPhase === "Development" || newSdlcPhase === "QA") &&
                (newAppDevOrigin === "Internally" || newAppDevOrigin === "Outsourced")) {

                var webAppBlocks = $scope.questionnaireInfoUIObject.applications.filter(containsWebAppInfoBlocks($scope.currentWebApplicationUIObject));

                if(webAppBlocks.length == 0) {
                    $scope.currentWebApplicationUIObject = new WebAppInfo().toUIObject();
                    $scope.questionnaireInfoUIObject.applications.push($scope.currentWebApplicationUIObject);
                    $scope.questionnaireInfoUIObject.applications.push(new WebAppInfo().toUIObject(10));
                }

                $state.go('questionnaire.webapp');
            }
        }
    });*/

    $scope.startNewApplicationWizard = function() {
        $state.go('questionnaire.wizard');
    };

    $scope.addApplication = function() {
        var stateInfo = {state: null}

        if(Object.isDefined($scope.questionnaireForm.applicationTypeFormElement)) {
            if($scope.questionnaireForm.applicationTypeFormElement === "Web") {
                $scope.questionnaireInfoUIObject.applications.push(new WebAppInfo({type: "Web"}).toUIObject());
                $scope.currentWebApplicationUIObject = $scope.questionnaireInfoUIObject.applications[$scope.questionnaireInfoUIObject.applications.length -1];
                stateInfo.state = 'questionnaire.webapp';
            } else if($scope.questionnaireForm.applicationTypeFormElement === "Web Service") {
                $scope.questionnaireInfoUIObject.applications.push(new WebAppInfo({type: "Web Service"}).toUIObject());
                $scope.currentWebApplicationUIObject = $scope.questionnaireInfoUIObject.applications[$scope.questionnaireInfoUIObject.applications.length -1];
                stateInfo.state = 'questionnaire.webservice';
            } else if($scope.questionnaireForm.applicationTypeFormElement === "Mobile") {
                $scope.questionnaireInfoUIObject.applications.push(new MobileAppInfo({type: "Mobile"}).toUIObject());
                $scope.currentWebApplicationUIObject = $scope.questionnaireInfoUIObject.applications[$scope.questionnaireInfoUIObject.applications.length -1];
                stateInfo.state = 'questionnaire.mobile';
            } else if($scope.questionnaireForm.applicationTypeFormElement === "Other") {
                $scope.questionnaireInfoUIObject.applications.push(new WebAppInfo({type: "Other"}).toUIObject());
                $scope.currentWebApplicationUIObject = $scope.questionnaireInfoUIObject.applications[$scope.questionnaireInfoUIObject.applications.length -1];
                stateInfo.state = 'questionnaire.other';
            }
        }

        if(Object.isDefined($scope.questionnaireForm.appHostedLocationFormElement)) {
            if($scope.questionnaireForm.appHostedLocationFormElement === "KP Data Centers") {
                $scope.questionnaireInfoUIObject.applications.push(new WafAppInfo({type: "WAF"}).toUIObject());
                $scope.currentWafApplicationUIObject = $scope.questionnaireInfoUIObject.applications[$scope.questionnaireInfoUIObject.applications.length -1];
            } else if($scope.questionnaireForm.appHostedLocationFormElement === "Vendor Hosted") {

            }
        }

        $state.go(stateInfo.state);
    };

    $scope.addIpAddress = function() {
        if(Object.isNotDefined($scope.currentWafApplicationUIObject)) {
            //TODO: throw exception and display error message.
            console.error("expected to currentWafApplicationUIObject to be defined.");
        }

        $scope.currentWafApplicationUIObject.loadBalancerIpAddresses.push($scope.questionnaireForm.loadBalancerIpAddressFormElement);

    };

    $scope.removeIpAddress = function(ipAddress) {
        var ipAddressIndexInfo = {index: null, ipAddress: null};
        if($scope.currentWafApplicationUIObject.loadBalancerIpAddresses.some(containsIpAddress(ipAddress, ipAddressIndexInfo))){
            $scope.currentWafApplicationUIObject.loadBalancerIpAddresses.splice(ipAddressIndexInfo.index, 1);
        } else {
            //TODO: throw exception and display error message.
            console.error("expected to find IP Address with value of: " + ipAddress);
        }
    };

    $scope.addCredentials = function() {
        if (Object.isNotDefined($scope.currentWebApplicationUIObject)) {
            //TODO: throw exception and display error message.
            console.error("expected to currentWebApplicationUIObject to be defined.");
        }
        var jsonCredentialConfig = {
            "username": $scope.credentialFormElement.username,
            "password": $scope.credentialFormElement.password
        };

        $scope.currentWebApplicationUIObject.credentials.push(new CredentialInfo(jsonCredentialConfig).toUIObject());
    };

    $scope.removeCredentials = function(credentialGUID) {
        var credentialIndexInfo = {index: null, credential: null};
        if($scope.currentWebApplicationUIObject.credentials.some(containsCredentialsWithGUID(credentialGUID, credentialIndexInfo))){
            $scope.currentWebApplicationUIObject.credentials.splice(credentialIndexInfo.index, 1);
        } else {
            //TODO: throw exception and display error message.
            console.error("expected to find credentials with guid: " + credentialGUID);
        }
    };

    $scope.applicationTypeNotSelected = function() {
        if(Object.isDefined($scope.questionnaireForm.applicationTypeFormElement)){
            return false;
        }

        return true;
    };

    $scope.removeApplication = function (targetApplication) {
        $scope.questionnaireInfoUIObject.applications.some(function(application, index, array){
           if (targetApplication.guid === application.guid) {
               array.splice(index, 1);
               return true;
           }
        });

        $state.go('questionnaire');

    };

    $scope.editApplication = function(application) {
        $scope.currentWebApplicationUIObject = application;
        $state.go('questionnaire.webapp');
    };

    $scope.submit = function() {

    };

    $scope.isWebAppInProduction = function () {
        return false;
    };

    $scope.showAppHostedLocationSection = function () {
        if(($scope.questionnaireInfoUIObject.sdlcPhase === "Production" &&
            $scope.questionnaireForm.applicationTypeFormElement === 'Web')){
            return true;
        } else {
            return false;
        }
    };

    $scope.showApplicationSection = function () {
        if(Object.isNotDefined($scope.questionnaireInfoUIObject.sdlcPhase) ||
            $scope.questionnaireInfoUIObject.sdlcPhase === "Design" ||
            $scope.questionnaireInfoUIObject.sdlcPhase === 'Requirements'){
            return false;
        } else {
            return true;
        }
    };
}]);