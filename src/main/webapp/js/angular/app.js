myApp = angular.module('App', ['ui.router']);
myApp.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
    $urlRouterProvider.otherwise("home");

        $stateProvider
            .state('home', {
                url: '/home',
                templateUrl: 'partials/home.html'
            })
            .state('engage', {
                url: '/engage',
                templateUrl: 'partials/questionnaire.html'
            })
            .state('questionnaire', {
                url: '/questionnaire',
                templateUrl: 'partials/views/questionnaire/base.questionnaire.html',
                controller: 'questionnaireController'
            })
            .state('questionnaire.wizard', {
                url: '/wizard',
                templateUrl: 'partials/views/questionnaire/new.app.wizard.html',
            })
            .state('questionnaire.webapp', {
                url: '/sourcecode',
                templateUrl: 'partials/views/questionnaire/web.app.questionnaire.html',
            })
            .state('questionnaire.webservice', {
                url: '/sourcecode',
                templateUrl: 'partials/views/questionnaire/webservice.app.questionnaire.html',
            })
            .state('questionnaire.mobile', {
                url: '/sourcecode',
                templateUrl: 'partials/views/questionnaire/mobile.app.questionnaire.html',
            })
            .state('questionnaire.other', {
                url: '/sourcecode',
                templateUrl: 'partials/views/questionnaire/other.app.questionnaire.html',
            })
    }]);