function execute() {
        document.getElementById('hiddenText').style.display="block";
        // how does this work?? 
        show('sport','sport');
        show('team', 'team');
        show('stat1', 'firstStatistic');
        show('stat2', 'secondStatistic');
        show('analyze', 'analysis');
}
function show(id, id_name){
        document.getElementByID(id).innerHTML = document.getElementByID(id_name).value;
}