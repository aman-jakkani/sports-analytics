$(document).ready(function() {


  $("#sport, #league").change(function() {
    if ($(this).attr('id') == 'sport') {
      var value = $(this).val();
      $("#league").html(options[value]);
    } else if ($(this).attr('id') == 'league') {
      var value1 = $("#league").val();
      $("#team").html(options1[value1]);
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
  "<option value='0'>--Make a choice--</option><option value='1'>FC Barcelona</option><option value='2'>Real Madrid</option>",
  "<option value='0'>--Make a choice--</option><option value='1'>Manchester United</option><option value='2'>Arsenal</option>"
  ];
});


