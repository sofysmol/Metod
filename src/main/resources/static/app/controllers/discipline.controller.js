function DisciplineController($scope, discipline, kafedra, specialy, sheduler, pages){
    var self = this
    $scope.page = pages[3]
    $scope.page.mainHeader = discipline.name
    $scope.item = {
                      spec:specialy.name,
                      kaf: kafedra.name,
                      form: specialy.codeForm
                  }
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
DisciplineController.resolve = {
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
    specialy:function($route, Specialties){
            let params = $route.current.params
            return Specialties.getByCodeKafAndForm(
                params.codeKaf, params.codeForm, params.codeSpec)
        },
    pages:function(MainSlavePages){
                      return MainSlavePages.query();
    }

}
