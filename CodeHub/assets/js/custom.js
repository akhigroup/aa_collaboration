var setting = function() {

  $("input:checkbox").on('click', function() {
  // in the handler, 'this' refers to the box clicked on
  var $box = $(this);
  if ($box.is(":checked")) {
    // the name of the box is retrieved using the .attr() method
    // as it is assumed and expected to be immutable
    var group = "input:checkbox[name='" + $box.attr("name") + "']";
    // the checked state of the group/box on the other hand will change
    // and the current value is retrieved using .prop() method
    $(group).prop("checked", false);
    $box.prop("checked", true);
  } else {
    $box.prop("checked", false);
  }
});

//For 15 years to select from birthdate datepicker
$('.datepicker').pickadate({
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 100,   // Creates a dropdown of 15 years to control year
  });

$(document).ready(function(){
    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    $('.modal').modal();
  });

$(document).ready(function() {
    $('select').material_select();
  });

   $(document).ready(function() {
    $('input#reg_username,#first_name,#last_name,#reg_password,#confirm_password,#email, textarea#textarea1').characterCounter();
  });

  $(document).ready(function(){
    $('.tooltipped').tooltip({delay: 50});
  });
}

setting();
	