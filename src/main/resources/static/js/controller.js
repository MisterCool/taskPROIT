myApp.controller('myController', 
    ['$scope', '$http', 'ngDialog',
    function($scope, $http, ngDialog) {

    $http.get('http://localhost:7001/TaskPROIT-1.0-SNAPSHOT/resources/users/tree')
    .then(function(response) {
        $scope.dataTree = response.data;
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
      $scope.selectNode = function(selectedPerson) {
        $scope.selected = selectedPerson;
    };


    $scope.showForm = function(){

    ngDialog.openConfirm({
        template: 'view/form.html',
        className: 'ngdialog-theme-default',
        controller: 'myController',
        scope: $scope
        });
    }

    $scope.close = function(){
        ngDialog.close();
    }
    $scope.submitForm = function()
    {
        var config = {
            headers : {
                'Content-Type':'application/json'
            }
        }
        if($scope.selected == undefined)
        {
            var dataPerson = {
                parentId: null,
                name: $scope.name,
                surname: $scope.surname,
            };
        } else { 
            var dataPerson = 
        {
            parentId: $scope.selected.id,
            name: $scope.name,
            surname: $scope.surname,
        };
    }
        var url = 'http://localhost:8080/TaskPROIT-1.0-SNAPSHOT/resources/users';

        $http.post(url, dataPerson, config).then(function(response) {
            if ($scope.selected) {
                $scope.selected.children.push(response.data);
            } else {
                $scope.dataTree.push(response.data);
            }      
        });
        
        $scope.name = "";
        $scope.surname = "";
    };
 
    $scope.deletePerson = function()
    {
        var url = 'http://localhost:8080/TaskPROIT-1.0-SNAPSHOT/resources/users/'
         + $scope.selected.id;

         $http.delete(url).then(function(response) {
             $http.get('http://localhost:7001/TaskPROIT-1.0-SNAPSHOT/resources/users/tree')
            .then(function(response) {
        $scope.dataTree = response.data;
    });
         });
    }
        var dataPerson = 
        {
            "parentId": "null",
            "name": $scope.name,
            "surname": $scope.surname
        };

        $http.post(url, dataPerson, config).then(function(response) {
            $scope.findPersons = response.data;
        });
    }

  }]);
