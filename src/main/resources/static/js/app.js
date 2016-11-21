/**
 * Created by hongkailiu on 2016-11-07.
 */

var app = angular.module('myApp', []);

app.controller('myCtrl', function ($scope, $http, $timeout) {
    $scope.reload = function () {
        $http.get("/monitor/lineChartData").then(function (response) {
            var data = response.data;
            console.log(data);
            var date1 = getDate(data.date1);
            var date2 = getDate(data.date2);
            var rows = data.rows;
            var points1 = [];
            var points2 = [];
            rows.forEach(function (row) {
                //
                if (row.y1 != -1) {
                    points1.push(
                        {
                            x: new Date(2012, 06, 15, getLocalDate(row.time)
                                .getHours(), getLocalDate(row.time).getMinutes(), getLocalDate(
                                row.time).getSeconds()), y: row.y1
                        });
                }
                if (row.y2 != -1) {
                    points2.push(
                        {
                            x: new Date(2012, 06, 15, getLocalDate(row.time)
                                .getHours(), getLocalDate(row.time).getMinutes(), getLocalDate(
                                row.time).getSeconds()), y: row.y2
                        });
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
                        title: "response time (ms)",
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

function getDate(milliseconds) {
    var d = getLocalDate(milliseconds);
    var day = d.getDate();
    // http://www.w3schools.com/jsref/jsref_obj_date.asp
    var month = d.getMonth() + 1; // 0 - 11
    var year = d.getFullYear();

    return year + "-" + month + "-" + day;
}

function getLocalDate(milliseconds) {
    var d = new Date(0); // The 0 there is the key, which sets the date to the epoch
    d.setUTCSeconds(milliseconds / 1000);
    return d;
}
