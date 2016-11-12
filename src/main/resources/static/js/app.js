/**
 * Created by hongkailiu on 2016-11-07.
 */

var app = angular.module('myApp', []);

app.controller('myCtrl', function ($scope, $http, $timeout) {
    $scope.reload = function () {
        $http.get("/monitor/lineChartData").then(function (response) {
            var data = response.data;
            console.log(data)
            var date1 = data.date1;
            var date2 = data.date2;
            var rows = data.rows;
            var points1 = [];
            var points2 = [];
            rows.forEach(function(row) {
                //{"hour":0,"minute":0,"second":0,"y1":-1,"y2":-1}
                if (row.y1!=-1) {
                    points1.push({x: new Date(2012, 06, 15, row.hour, row.minute, row.second), y: row.y1});
                }
                if (row.y2!=-1) {
                    points2.push({x: new Date(2012, 06, 15, row.hour, row.minute, row.second), y: row.y2});
                }
            });

            var chart = new CanvasJS.Chart("chartContainer",
                {
                    title: {
                        text: "Calculator Service Monitoring"
                    },
                    axisX: {
                        title: "poking time",
                        valueFormatString: "HH:mm:ss",
                        labelAngle: -50
                    },
                    axisY: {
                        title : "response time (ms)",
                        valueFormatString: "#,###"
                    },

                    data: [
                        {
                            type: "line",
                            color: "#00FF00",
                            showInLegend: true,
                            name: "series1",
                            legendText: date1,
                            dataPoints: points1
                        },
                        {
                            type: "line",
                            color: "#0000FF",
                            showInLegend: true,
                            name: "series2",
                            legendText: date2,
                            dataPoints: points2
                        }

                    ]
                });

            chart.render();
            
        });

        $timeout(function () {
            $scope.reload();
        }, 30000)
    };
    $scope.reload();
});