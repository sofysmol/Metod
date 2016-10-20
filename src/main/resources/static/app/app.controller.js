/**
 * Created by user on 8/28/16.
 */
 function AppController($scope, otcDynamic, fakultets){
    var self = this
    $scope.fakultets = fakultets



}
AppController.resolve = {

    fakultets:function(Fakultets) {
            return Fakultets.query();

    }/*,
    kafedras:function(Kafedras) {
                return Kafedras.query();
        },
    spetialties:function(Spetialties) {
                return Spetialties.query();
        }*/
}