/**
 * Created by user on 8/28/16.
 */
 function AppController($scope, otcDynamic, faculties, kafedras,
                        tables, specialties, disciplines, tabs){
    var self = this
    $scope.tabs = tabs
    $scope.tables = tables
    $scope.tableContent = [faculties, kafedras, specialties, disciplines]
    $scope.addController = openAdd1
    $scope.deleteController = openDelete1
    $scope.editController = openEdit1
    $scope.templateAddition = 'additionModal.html'
    $scope.templateEdit = 'editModal.html'
    $scope.canEdit = true
    $scope.canHref = true
    angular.forEach($scope.tableContent, function(content, key){
        angular.forEach(content, function(s, k){
            s.params = ""
        })
    })
    /*for(s in $scope.tableContent[0])
                    s.params = ""
    for(s in $scope.tableContent[1])
                        s.params = ""
    for(s in $scope.tableContent[2])
                        s.params = ""
    for(s in $scope.tableContent[3])
                        s.params = ""*/
    $scope.index = 0
    self.switchTab = function(index){
        $scope.index = index
    }


}
AppController.resolve = {

    faculties:function(Faculties) {
            return Faculties.query();
            },
    kafedras:function(Kafedras){
            return Kafedras.query();
    },
    tablesList:function(TablesList){
            return TablesList.query();
    },
    specialties:function(Specialties) {
            return Specialties.query();
        },
    disciplines:function(Disciplines) {
            return Disciplines.query();
    },
    tabs:function(TabsList){
                  return TabsList.query();
    },
    tables:function(TablesList){
                        return TablesList.query();
          }
}