angular.module('app', ['treeControl'])
    .controller('myController', ['$scope', function($scope) {
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
        $scope.dataTree = [{
            "id":"1",
            "name": "Joe",
            "children": [{
                "id": "2",
                "name": "Smith",
                "children": []
            },
                {
                    "id": "3",
                    "name": "Gary",
                    "children": [{
                        "id" : "4",
                        "name": "Jenifer",
                        "children": [{
                            "id" : "5",
                            "name": "Dani",
                            "children": []
                        },
                            {
                                "id" : "6",
                                "name": "Max",
                                "children": []
                            }
                        ]
                    }]
                }
            ]
        },
            {
                "id" : "7",
                "name": "Albert",
                "children": []
            },
            {
                "id" : "8",
                "name": "Ron",
                "children": []
            }
        ];

        $scope.addRoot = function()
        {
            $scope.dataTree.push({id:"9", name:"Vlad", children: "[]"});
        };
    }]);