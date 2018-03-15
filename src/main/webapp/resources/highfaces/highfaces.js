var hfLiveUpdate = function (comp) {
    try {
        jsf.ajax.addOnEvent(function (data) {
            if (data.status == "success") {
                var updates = data.responseXML.firstChild.getElementsByTagName("extension");
                var id = updates[0].getAttribute("id");
                var element = document.getElementById(id);
                var nodename = "#" + (id.split(":").join("_")) + "_chart";
                var hc = $(nodename);
                var chart = Highcharts.charts[hc.data('highchartsChart')];
                var json = JSON.parse(updates[0].innerHTML);
                var doRedraw = false;

                for (i = 0; i < json.series.length; i++) {
                    var data = json.series[i].data;
                    var lastk = 0;
                    for (j = 0; j < data.length; j++) {
                        var item = data[j];
                        // first check, if we alter an entry
                        var found = false;
                        for (k = lastk; k < chart.series[i].data.length && !found; k++) {
                            if (chart.series[i].data[k].x == item[0]) {
                                chart.series[i].data[k].update(item, false);
                                found = true;
                                lastk = k;
                            }
                        }
                        if (!found) {
                            if (chart.series[i].data.length > chart.options.chart.capacity) {
                                chart.series[i].removePoint(0, false);
                                chart.series[i].addPoint(item, false, false);
                            } else
                                chart.series[i].addPoint(item, false, false);
                            lastk = chart.series[i].data.length;
                        }
                        //console.log("data.length="+chart.series[i].data.length);
                    }
                }

                chart.redraw();

            }
        });
        jsf.ajax.request(comp[0].id, null, {'javax.faces.behavior.event': 'update', 'execute': '@form', 'render': comp[0].id + ':update'});
    } catch (e) {
        console.log(e);
    }
};