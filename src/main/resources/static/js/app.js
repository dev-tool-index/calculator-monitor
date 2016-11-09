/**
 * Created by hongkailiu on 2016-11-07.
 */

var app = angular.module('myApp', []);

app.controller('myCtrl', function ($scope, $http, $timeout) {
    $scope.firstName = "John";
    $scope.lastName = "Doe";
    $scope.reload = function () {
        $http.get("/monitor").then(function(response) {
            var data = response.data;
            if(data.length != 0) {
                $scope.data.forEach(function(d) {
                    d["voted"] = false;

                });
            }
        });

        $timeout(function(){
            $scope.reload();
        },30000)
    };
    $scope.reload();
});