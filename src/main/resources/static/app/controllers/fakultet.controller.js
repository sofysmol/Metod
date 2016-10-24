function FakultetController($scope, fakultet, kafedras, tables){
    var self = this
    $scope.item = fakultet
    $scope.tables = tables
    $scope.index = 1
    $scope.tableContent = []
    $scope.tableContent[1] = kafedras
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
        }

}
