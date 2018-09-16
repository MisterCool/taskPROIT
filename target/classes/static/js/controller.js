angular.module('app', ['treeControl'])
    .controller('myController', ['$scope','$http', function($scope, $http) {

        $http.get('http://localhost:7001/TaskPROIT-1.0-SNAPSHOT/resources/users/tree').success(function(data) {
            $scope.dataTree = data;
        });
        $scope.treeOptions = {
            nodeChildren: "children",
            dirSelectable: true,
            injectClasses: {
                ul: "a1",
                li: "a2",
                liSelected: "a7",
                iExpanded: "a3",
                iCollapsed: "a4",
                iLeaf: "a5",
                label: "a6",
                labelSelected: "a8"
            }
        }


        $scope.clearSelected = function() {
            $scope.selected = undefined;
        }
        $scope.selectNode = function(sel) {
            $scope.selected = sel;
            $scope.selectedId = sel.id;
        };

        $scope.submitForm = function()
        {
            var config = {
                headers : {
                    'Content-Type':'application/json'
                }
            }
            if($scope.selected == undefined)
            {
                var dataPerson =
                    {
                        "parentId": "null",
                        "name": $scope.name,
                        "surname": $scope.surname,
                    };
            }
            else {
                var dataPerson =
                    {
                        "parentId": $scope.selected.id,
                        "name": $scope.name,
                        "surname": $scope.surname,
                    };
            }
            var url = 'http://localhost:8080/TaskPROIT-1.0-SNAPSHOT/resources/users';
            $http.post(url, dataPerson, config).then(function(response) {
                $scope.postResultMessage = response.data;
            });
            $scope.name = "";
            $scope.surname = "";
        };

        $scope.deletePerson = function()
        {
            var url = 'http://localhost:8080/TaskPROIT-1.0-SNAPSHOT/resources/users/'
                + $scope.selected.id;

            $http.delete(url).then(function(response) {
            });
        }
    }]);