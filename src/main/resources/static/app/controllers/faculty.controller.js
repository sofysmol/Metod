function FacultyController($scope, faculty, kafedras, tables, pages){
    var self = this
    $scope.page = pages[0]
    $scope.item = faculty
    $scope.tables = tables
    $scope.index = 1
    $scope.addController = openAddKafByFak(faculty.code)
    $scope.deleteController = openDelete1
    $scope.editController = openEdit1
    $scope.templateAddition = 'additionModal.html'
    $scope.templateEdit = 'editModal.html'
    $scope.canEdit=true
    $scope.canHref = true
    $scope.tables[$scope.index] = { name: $scope.page.slaveName,
                                    headers:$scope.page.slaveHeaders,
                                    keys:$scope.page.slaveKeys}
    $scope.tableContent = []
    $scope.tableContent[$scope.index] = kafedras
    angular.forEach($scope.tableContent[$scope.index], function(value, k){
        value.params=""
        })
}
FacultyController.resolve = {
    faculty:function($route, Faculties){
        return Faculties.getByCode($route.current.params.code)
    },
    kafedras:function($route, Kafedras){
        return Kafedras.getByCodeFakultet($route.current.params.code)
    },
    tables:function(TablesList){
                      return TablesList.query();
    },
    pages:function(MainSlavePages){
                      return MainSlavePages.query();
    }

}
