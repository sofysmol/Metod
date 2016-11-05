/**
 * Created by user on 9/8/16.
 */
 String.prototype.format = function(...args) {
      let result = this.toString();
      let i = 0;
      for (let arg of args) {
           let strToReplace = "{" + i++ + "}";
           result = result.replace(strToReplace, (arg || ''));
      }
      return result;
 };
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
.factory('Faculties', function($http){
       var Faculties = function(data){
           angular.copy(data, this);
       };
       Faculties.query = function() {
           return $http.get('/faculties').then(makeArray(Faculties));
       }
       Faculties.getByCode = function(code) {
               return $http.get('/faculties?code='+code).then(instantiate(Faculties));
           }

       return Faculties;
 }).factory('Kafedras', function($http){
                var Kafedras = function(data){
                    angular.copy(data, this);
                };
                Kafedras.query = function() {
                    return $http.get('/kafedras').then(makeArray(Kafedras));
                }
                Kafedras.getByCodeFakultet = function(code){
                    return $http.get('/faculties/'+code+'/kafedras').then(makeArray(Kafedras));
                }
                Kafedras.getByCode = function(code) {
                               return $http.get('/kafedras/'+code).then(instantiate(Kafedras));
                           }
                return Kafedras;
   }).factory('Specialties', function($http){
                var Specialties = function(data){
                    angular.copy(data, this);
                };
                Specialties.query = function() {
                    return $http.get('/specialties').then(makeArray(Specialties));
                }
                Specialties.getByCodeKafedra = function(code){
                    return $http.get("/specialties?code-kaf="+code).then(makeArray(Specialties));
                }
                Specialties.getByCodeKafAndForm = function(codeKaf, codeForm, codeSpec){
                    return $http.get("/specialties?code-kaf={0}&code-form={1}&code-spec={2}"
                        .format(codeKaf, codeForm, codeSpec)).then(instantiate(Specialties));
                }
                return Specialties;
     }).factory('Disciplines', function($http){
                 var Disciplines = function(data){
                     angular.copy(data, this);
                 };
                 Disciplines.query = function() {
                     return $http.get('/disciplines').then(makeArray(Disciplines));
                 }
                 Disciplines.getByCode =function(code){
                    return $http.get('/disciplines/'+code).then(instantiate(Disciplines));
                 }
                 Disciplines.getByCodeSpecialty = function(codeSpec,codeForm, codeKaf){
                    return $http.get('/disciplines?code-kaf={0}&code-form={1}&code-spec={2}'.format(codeKaf,
                                        codeForm, codeSpec)).then(makeArray(Disciplines));
                                 }
                 Disciplines.getByCodeDisAndSpec = function(codeDis,codeSpec,codeForm, codeKaf){
                    return $http.get('/disciplines?code-dis={3}&code-kaf={0}&code-form={1}&code-spec={2}'.format(codeKaf,
                                        codeForm, codeSpec, codeDis)).then(instantiate(Disciplines));
                                 }
                 return Disciplines;
     }).factory('Sheduler', function($http){
                         var Sheduler = function(data){
                             angular.copy(data, this);
                         };
                         Sheduler.query = function(codeDis, codeKaf,codeForm, codeSpec) {
                            return $http.get('/sheduler?code-dis={3}&code-kaf={0}&code-form={1}&code-spec={2}'.format(codeKaf,
                                             codeForm, codeSpec, codeDis)).then(makeArray(Sheduler));
                         }
                         return Sheduler;
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
     }).factory('MainSlavePages', function($http){
                       var MainSlavePages = function(data){
                           angular.copy(data, this);
                       };
                       MainSlavePages.query = function() {
                           return $http.get('app/resources/tables-with-slave-table.json').then(makeArray(MainSlavePages));
                       }
                       return MainSlavePages;
      })