/**
 * Created by user on 9/8/16.
 */
function makeArray(Type) {
    return function(response) {
        var list = [];
        angular.forEach(response.data, function(data) {
            list.push(new Type(data));
        });
        return list;
    }
}

function instantiate(Type) {
    return function(response) {
        return new Type(response.data);
    }
}
angular.module('dataServices', [])
.factory('Fakultets', function($http){
       var Fakultets = function(data){
           angular.copy(data, this);
       };
       Fakultets.query = function() {
           return $http.get('/fakultets').then(makeArray(Fakultets));
       }
       Fakultets.getByCode = function(code) {
               return $http.get('/fakultets/'+code).then(instantiate(Fakultets));
           }
       return Fakultets;
 }).factory('Kafedras', function($http){
                var Kafedras = function(data){
                    angular.copy(data, this);
                };
                Kafedras.query = function() {
                    return $http.get('/kafedras').then(makeArray(Kafedras));
                }
                Kafedras.getByCodeFakultet = function(code){
                    return $http.get('/fakultets/'+code+'/kafedras').then(makeArray(Kafedras));
                }
                return Kafedras;
   }).factory('Specialties', function($http){
                var Specialties = function(data){
                    angular.copy(data, this);
                };
                Specialties.query = function() {
                    return $http.get('/specialties').then(makeArray(Specialties));
                }
                return Specialties;
     }).factory('Disciplines', function($http){
                 var Disciplines = function(data){
                     angular.copy(data, this);
                 };
                 Disciplines.query = function() {
                     return $http.get('/disciplines').then(makeArray(Disciplines));
                 }
                 return Disciplines;
      }).factory('TablesList', function($http){
         var TablesList = function(data){
             angular.copy(data, this);
         };
         TablesList.query = function() {
             return $http.get('app/resources/tables.json').then(makeArray(TablesList));
         }
         return TablesList;
     }).factory('TabsList', function($http){
                var TabsList = function(data){
                    angular.copy(data, this);
                };
                TabsList.query = function() {
                    return $http.get('app/resources/tabs.json').then(makeArray(TabsList));
                }
                return TabsList;
            })