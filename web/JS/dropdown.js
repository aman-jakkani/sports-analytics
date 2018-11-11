$(document).ready(function() {


  $("#sport, #league, #team, #season").change(function() {
    if ($(this).attr('id') == 'sport') {
      var value = $(this).val();
      $("#league").html(options[value]);
      var value4 = $(this).val();
      $("#firstStatistic").html(options4[value4]);
      var value5 = $(this).val();
      $("#secondStatistic").html(options5[value5]);
    } 
    else if ($(this).attr('id') == 'league') {
      var value1 = $("#league").val();
      $("#team").html(options1[value1]);
    }
	else if ($(this).attr('id') == 'team') {
      var value2 = $("#team").val();
      $("#season").html(options2[value2]);
    }
    else if ($(this).attr('id') == 'season') {
      var value3 = $("#season").val();
      $("#game").html(options3[value3]);
    }


  });


  var options = [
    "<option value='0'>--Make a choice--</option>",
    "<option value='0'>--Make a choice--</option><option value='1'>NBA</option>",
    "<option value='0'>--Make a choice--</option><option value='2'>La Liga</option><option value='3'>Premier League</option>"
  ];


  var options1 = [
  "<option value='0'>--Make a choice--</option>", 
  "<option value='0'>--Make a choice--</option><option value='1'>Houston Rockets</option><option value='2'>LA Lakers</option>", 
  "<option value='0'>--Make a choice--</option><option value='3'>FC Barcelona</option><option value='4'>Real Madrid</option>",
  "<option value='0'>--Make a choice--</option><option value='5'>Manchester United</option><option value='6'>Arsenal</option>"
  ];
  
  var options2 = [
  "<option value='0'>--Make a choice--</option>", 
  "<option value='0'>--Make a choice--</option><option value='1'>2017</option><option value='2'>2018</option>", 
  "<option value='0'>--Make a choice--</option><option value='3'>2017</option><option value='4'>2018</option>",
  "<option value='0'>--Make a choice--</option><option value='5'>2010</option><option value='6'>2011</option>",
  "<option value='0'>--Make a choice--</option><option value='7'>2013</option><option value='8'>2014</option>",
  "<option value='0'>--Make a choice--</option><option value='9'>2010</option><option value='10'>2011</option>",
  "<option value='0'>--Make a choice--</option><option value='11'>2013</option><option value='12'>2014</option>"
  ];
  
  var options3 = [
  "<option value='0'>--Make a choice--</option>", 
  "<option value='0'>--Make a choice--</option><option value='1'>1</option><option value='2'>2</option>", 
  "<option value='0'>--Make a choice--</option><option value='3'>3</option><option value='4'>4</option>",
  "<option value='0'>--Make a choice--</option><option value='5'>5</option><option value='6'>6</option>",
  "<option value='0'>--Make a choice--</option><option value='7'>7</option><option value='8'>8</option>",
  "<option value='0'>--Make a choice--</option><option value='9'>9</option><option value='10'>10</option>",
  "<option value='0'>--Make a choice--</option><option value='11'>11</option><option value='12'>12</option>",
  "<option value='0'>--Make a choice--</option><option value='13'>13</option><option value='14'>14</option>", 
  "<option value='0'>--Make a choice--</option><option value='15'>15</option><option value='16'>16</option>",
  "<option value='0'>--Make a choice--</option><option value='17'>17</option><option value='18'>18</option>",
  "<option value='0'>--Make a choice--</option><option value='19'>19</option><option value='20'>20</option>",
  "<option value='0'>--Make a choice--</option><option value='21'>21</option><option value='22'>22</option>",
  "<option value='0'>--Make a choice--</option><option value='23'>23</option><option value='24'>24</option>"
  ];
  
  var options4 = [
    "<option value='0'>--Make a choice--</option>",
    "<option value='0'>--Make a choice--</option><option value='1'>Rebounds</option><option value='2'>Shooting Percentage</option>",
    "<option value='0'>--Make a choice--</option><option value='3'>Goals</option><option value='4'>Assists</option>"
  ];
  
  var options5 = [
    "<option value='0'>--Make a choice--</option>",
    "<option value='0'>--Make a choice--</option><option value='1'>Rebounds</option><option value='2'>Shooting Percentage</option>",
    "<option value='0'>--Make a choice--</option><option value='3'>Goals</option><option value='4'>Assists</option>"
  ];
  
});


