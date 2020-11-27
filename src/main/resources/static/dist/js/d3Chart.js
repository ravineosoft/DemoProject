function callChart()
{

    var test_data = $("#chart_server_data").text(); 
    var data = JSON.parse(test_data);
	var chartdata = [];
	data.forEach(function(d) {
        d.Letter = d.Letter;
        d.Freq = +d.Freq;
		chartdata.push(d.Freq);
    });

	var height = 300,
                 width = 720,
                 barWidth = 40,
                 barOffset = 20;
              
             var yScale = d3.scale.linear()
                     .domain([0, d3.max(chartdata)])
                     .range([0, height])
              
             d3.select('#bar-chart').append('svg')
               .attr('width', width)
               .attr('height', height)
               .style('background', '#dee2e6')
               .selectAll('rect').data(chartdata)
               .enter().append('rect')
                 .style({'fill': 'steelblue', 'stroke': '', 'stroke-width': '5'})
                 .attr('width', barWidth)
                 .attr('height', function (data) {
                     return yScale(data);
                 })
                 .attr('x', function (data, i) {
                     return i * (barWidth + barOffset);
                 })
                 .attr('y', function (data) {
                     return height - yScale(data);
                 });


	



 
}