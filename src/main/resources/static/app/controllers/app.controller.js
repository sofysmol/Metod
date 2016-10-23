/**
 * Created by user on 8/28/16.
 */
 function AppController($scope, otcDynamic, fakultets, kafedras, tableList){
    var self = this
    $scope.tableList = tableList//Names = ["fakultets", "kafedras"]
    $scope.tableContent = [fakultets, kafedras]
    $scope.index = 0

    self.switchTab = function(index){
        $scope.index = index
    }


}
AppController.resolve = {

    fakultets:function(Fakultets) {
            return Fakultets.query();
            },
    kafedras:function(Kafedras){
            return Kafedras.query();
    },
    tableList:function(TablesList){
            return TablesList.query();
    }

    /*,
    spetialties:function(Spetialties) {
                return Spetialties.query();
        }*/
}