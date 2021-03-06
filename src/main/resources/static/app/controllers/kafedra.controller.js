function KafedraController($scope, kafedra, specialties, allSpecialties, tables, pages){
    var self = this
    $scope.page = pages[1]
    $scope.item = kafedra
    $scope.tables = tables
    $scope.index = 2
    $scope.addController = openAddSpecByKaf(kafedra.code)
    $scope.deleteController = openDeleteDefault("/kafedras/specialty")
    $scope.editController = openEditNone
    $scope.canEdit = false
    $scope.canHref = true
    $scope.templateAddition = 'additionModalWithChoiceSpec.html'
    $scope.templateEdit = 'editModal.html'
    $scope.allSpecialties = allSpecialties
     $scope.forms = {
        options: ["ОФО","ЗФО"],
        selected: "ОФО"
     }
    $scope.tables[$scope.index] = { name: $scope.page.slaveName,
                                    headers:$scope.page.slaveHeaders,
                                    keys:$scope.page.slaveKeys}
    $scope.tableContent = []
    $scope.tableContent[$scope.index] = specialties
    angular.forEach($scope.tableContent[$scope.index], function(s, k){
            s.params="/"+encodeURI(kafedra.code)+"/"+encodeURI(s.codeForm)
            })
}
KafedraController.resolve = {
    kafedra:function($route, Kafedras){
        return Kafedras.getByCode($route.current.params.code)
    },
    specialties:function($route, Specialties){
        return Specialties.getByCodeKafedra($route.current.params.code)
    },
    tables:function(TablesList){
                      return TablesList.query();
    },
    pages:function(MainSlavePages){
                      return MainSlavePages.query();
    },
    allSpecialties: function(Specialties){
        return Specialties.query()
    }

}
