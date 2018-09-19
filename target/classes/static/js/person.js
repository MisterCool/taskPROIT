myApp.controller('person',
    ['$scope', '$http', '$routeParams',
        function ($scope, $http, $routeParams) {
            $scope.id = $routeParams.paramOne;

            $http.get('http://localhost:7001/TaskPROIT-1.0-SNAPSHOT/resources/users/' + $scope.id)
                .then(function (response) {
                    $scope.person = response.data;
                });

            $scope.saveUser = function () {
                var config = {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }
                var url = 'http://localhost:8080/TaskPROIT-1.0-SNAPSHOT/resources/users/edit';
                $http.post(url, $scope.person, config).then(function (response) {
                });
            };
        }]);