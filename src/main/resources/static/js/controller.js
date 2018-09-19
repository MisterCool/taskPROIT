myApp.controller('myController',
    ['$scope', '$http', 'ngDialog',
        function ($scope, $http, ngDialog) {

            $http.get('http://localhost:7001/TaskPROIT-1.0-SNAPSHOT/resources/users/tree')
                .then(function (response) {
                    $scope.dataTree = response.data;
                });

            $scope.selectNode = function (selectedPerson) {
                $scope.selected = selectedPerson;
            };


            $scope.showForm = function () {

                ngDialog.openConfirm({
                    template: 'view/form.html',
                    className: 'ngdialog-theme-default',
                    controller: 'myController',
                    scope: $scope
                });
            }

            $scope.addPerson = function () {
                var dataPerson = {
                    parentId: null,
                    name: $scope.name,
                    surname: $scope.surname
                }
                if ($scope.selected != undefined) {
                    dataPerson.parentId = $scope.selected.id;
                }
                var config = {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }
                var url = 'http://localhost:8080/TaskPROIT-1.0-SNAPSHOT/resources/users';
                $http.post(url, dataPerson, config).then(function (response) {
                    if ($scope.selected) {
                        $scope.selected.children.push(response.data);
                    } else {
                        $scope.dataTree.push(response.data);
                    }
                });
            }

            $scope.setExpanded = function () {
                var url = 'http://localhost:8080/TaskPROIT-1.0-SNAPSHOT/resources/users/search';
                var config = {
                    headers: {
                        'Content-Type': 'text/plain'
                    }
                }
                $http.post(url, $scope.inputString, config).then(function (response) {
                    $scope.expandedNodes = response.data;
                });
            };

            $scope.deletePerson = function () {
                var url = 'http://localhost:8080/TaskPROIT-1.0-SNAPSHOT/resources/users/'
                    + $scope.selected.id;

                $http.delete(url).then(function (response) {
                    $http.get('http://localhost:7001/TaskPROIT-1.0-SNAPSHOT/resources/users/tree')
                        .then(function (response) {
                            $scope.dataTree = response.data;
                            $scope.selected = undefined;
                        });
                });
            }
        }]);
