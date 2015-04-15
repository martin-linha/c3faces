function mergeWithFirstEqualZero(first, second) {
    var secondSet = d3.set();
    second.forEach(function (d) {
        secondSet.add(d.label);
    });

    var onlyFirst = first
            .filter(function (d) {
                return !secondSet.has(d.label)
            })
            .map(function (d) {
                return {label: d.label, value: 0, color: d.color, index: d.index};
            });
    return d3.merge([second, onlyFirst])
            .sort(function (a, b) {
                return d3.ascending(a.label, b.label);
            });
}

function change(data, dur, hoverEffect) {
    var duration = dur;
    var data0 = svg.select(".slices").selectAll("path.slice")
            .data().map(function (d) {
        return d.data
    });
    if (data0.length == 0)
        data0 = data;
    var was = mergeWithFirstEqualZero(data, data0);
    var is = mergeWithFirstEqualZero(data0, data);

    /* ------- SLICE ARCS -------*/

    var slice = svg.select(".slices").selectAll("path.slice")
            .data(pie(was), key);

    slice.enter()
            .insert("path")
            .attr("class", "slice")
            .style("fill", function (d) {
                return d.data.color;
            })
            .each(function (d) {
                this._current = d;
            });

    slice = svg.select(".slices").selectAll("path.slice")
            .data(pie(is), key);

    slice
            .transition().duration(duration)
            .attrTween("d", function (d) {
                var interpolate = d3.interpolate(this._current, d);
                var _this = this;
                return function (t) {
                    _this._current = interpolate(t);
                    return arc(_this._current);
                };
            }).each('end', function () {
                if(hoverEffect) {
        d3.select(this).on("mouseover", function (d) {
            d3.select(this).transition().duration(300).style({opacity: 0.5});
        })
                .on("mouseout", function () {
                    d3.select(this).transition().duration(200).style({opacity: 1});
                });
        ;
    
            }
        });


    slice = svg.select(".slices").selectAll("path.slice")
            .data(pie(data), key);

    slice
            .exit().transition().delay(duration).duration(0)
            .remove();

    /* ------- TEXT LABELS -------*/
    if (showLabels) {
        var text = svg.select(".labels").selectAll("text")
                .data(pie(was), key);

        text.enter()
                .append("text")
                .attr("dy", ".35em")
                .style("opacity", 0)
                .text(function (d) {
                    return d.data.label;
                })
                .each(function (d) {
                    this._current = d;
                });

        function midAngle(d) {
            return d.startAngle + (d.endAngle - d.startAngle) / 2;
        }

        text = svg.select(".labels").selectAll("text")
                .data(pie(is), key);

        text.transition().duration(duration)
                .style("opacity", function (d) {
                    return d.data.value == 0 ? 0 : 1;
                })
                .attrTween("transform", function (d) {
                    var interpolate = d3.interpolate(this._current, d);
                    var _this = this;
                    return function (t) {
                        var d2 = interpolate(t);
                        _this._current = d2;
                        var pos = outerArc.centroid(d2);
                        pos[0] = radius * (midAngle(d2) < Math.PI ? 1 : -1);
                        return "translate(" + pos + ")";
                    };
                })
                .styleTween("text-anchor", function (d) {
                    var interpolate = d3.interpolate(this._current, d);
                    return function (t) {
                        var d2 = interpolate(t);
                        return midAngle(d2) < Math.PI ? "start" : "end";
                    };
                });

        text = svg.select(".labels").selectAll("text")
                .data(pie(data), key);

        text
                .exit().transition().delay(duration)
                .remove();
        /* ------- SLICE TO TEXT POLYLINES -------*/

        var polyline = svg.select(".lines").selectAll("polyline")
                .data(pie(was), key);

        polyline.enter()
                .append("polyline")
                .style("opacity", 0)
                .each(function (d) {
                    this._current = d;
                });

        polyline = svg.select(".lines").selectAll("polyline")
                .data(pie(is), key);

        polyline.transition().duration(duration)
                .style("opacity", function (d) {
                    return d.data.value == 0 ? 0 : .5;
                })
                .attrTween("points", function (d) {
                    this._current = this._current;
                    var interpolate = d3.interpolate(this._current, d);
                    var _this = this;
                    return function (t) {
                        var d2 = interpolate(t);
                        _this._current = d2;
                        var pos = outerArc.centroid(d2);
                        pos[0] = radius * 0.95 * (midAngle(d2) < Math.PI ? 1 : -1);
                        return [arc.centroid(d2), outerArc.centroid(d2), pos];
                    };
                });

        polyline = svg.select(".lines").selectAll("polyline")
                .data(pie(data), key);

        polyline
                .exit().transition().delay(duration)
                .remove();

        /* ------- TOOLTIPS -------*/

        // TODO

    }
}
;