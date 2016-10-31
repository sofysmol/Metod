function FakultetController($scope, fakultet, kafedras, tables, pages){
    var self = this
    $scope.page = pages[0]
    $scope.item = fakultet
    $scope.tables = tables
    $scope.index = 1
    $scope.tables[$scope.index] = { name: $scope.page.slaveName,
                                    headers:$scope.page.slaveHeaders,
                                    keys:$scope.page.slaveKeys}
    $scope.tableContent = []
    $scope.tableContent[$scope.index] = kafedras
    angular.forEach($scope.tableContent[$scope.index], function(value, k){
        value.params=""
        })
}
FakultetController.resolve = {
    fakultet:function($route, Fakultets){
        return Fakultets.getByCode($route.current.params.code)
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
