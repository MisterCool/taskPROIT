var myApp = angular.module('myApp', ['ngRoute', 'ngDialog', 'treeControl', 'xeditable']).config(['$locationProvider', '$routeProvider',
    function config($locationProvider, $routeProvider, ngRoute, ngDialog) {
        $locationProvider.hashPrefix('!');

        $routeProvider.when('/person/:paramOne', {
            templateUrl: 'view/person.html',
            controller: 'person'
        }).when('/', {
            templateUrl: 'view/tree.html',
            controller: 'myController'
        });
    }
]);
