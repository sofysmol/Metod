function DisciplineSpecController($scope, discipline, kafedra, specialty, sheduler, pages){
    var self = this
    $scope.page = pages[3]
    $scope.page.mainHeader = discipline.name
    $scope.item = {
                      spec:specialty.name,
                      kaf: kafedra.name,
                      form: specialty.codeForm
                  }
    $scope.addController = openAddShedulerByDis(discipline.code, specialty)
    $scope.deleteController = openDeleteDefault("/disciplines/sheduler")
    $scope.editController = openEditSheduler(discipline.code, specialty.code,
                        specialty.codeForm, specialty.codeKaf)
    $scope.templateAddition = 'additionShedulerModal.html'
    $scope.templateEdit = 'editShedulerModal.html'
    $scope.canEdit = true
    $scope.tables = []
    $scope.index = 4
    $scope.tables[$scope.index] = { name: $scope.page.slaveName,
                                    headers:$scope.page.slaveHeaders,
                                    keys:$scope.page.slaveKeys}
    $scope.tableContent = []
    $scope.tableContent[$scope.index] = sheduler
    angular.forEach($scope.tableContent[$scope.index], function(value, k){
            value.params=""
        })
}
DisciplineSpecController.resolve = {
    sheduler:function($route, Sheduler){
        let params = $route.current.params
        return Sheduler.query(params.codeDis,
            params.codeKaf, params.codeForm, params.codeSpec)
    },
    discipline:function($route, Disciplines){
            return Disciplines.getByCode($route.current.params.codeDis)
        },
    kafedra:function($route, Kafedras){
        return Kafedras.getByCode($route.current.params.codeKaf)
    },
    specialty:function($route, Specialties){
            let params = $route.current.params
            return Specialties.getByCodeKafAndForm(
                params.codeKaf, params.codeForm, params.codeSpec)
        },
    pages:function(MainSlavePages){
                      return MainSlavePages.query();
    }

}
