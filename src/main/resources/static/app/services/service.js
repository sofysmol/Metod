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
angular.module('dataServices', []).
factory('Fakultet', function($http){
    var Fakultet = function(data){
        angular.copy(data, this);
    };
    Fakultet.query = function() {
        return $http.get('/fakultet').then(makeArray(Fakultet));
    }
    return Fakultet;
}).factory('Fakultets', function($http){
       var Fakultets = function(data){
           angular.copy(data, this);
       };
       Fakultets.query = function() {
           return $http.get('/fakultets').then(makeArray(Fakultets));
       }
       return Fakultets;
 })/*.factory('Specialties', function($http){
           var Specialties = function(data){
               angular.copy(data, this);
           };
           Specialties.query = function() {
               return $http.get('/specialties').then(makeArray(Specialties));
           }
           return Specialties;
}).factory('Kafedras', function($http){
             var Specialties = function(data){
                 angular.copy(data, this);
             };
             Kafedras.query = function() {
                 return $http.get('/kafedras').then(makeArray(Kafedras));
             }
             return Kafedras;
});/*.factory('Triggers', function($http){
    var Triggers = function(data){
        angular.copy(data, this);
    };

    Triggers.query = function() {
        return $http.get('/triggers').then(makeArray(Triggers));
    }
    return Triggers;
}).factory('Brands', function($http){
    var Brands = function(data){
        angular.copy(data, this);
    };

    Brands.query = function() {
        return $http.get('/brands').then(makeArray(Brands));
    }
    return Brands;
}).factory('Groups', function($http){
    var Groups = function(data){
        angular.copy(data, this);
    };

    Groups.query = function() {
        return $http.get('/groups').then(makeArray(Groups));
    }
    return Groups;
}).factory('Domain', function($http){
    var Domain = function(data){
        angular.copy(data, this);
    };

    Domain.query = function() {
        return $http.get('/domain').then(instantiate(Domain));
    }
    return Domain;
}).factory('Locales', function($http){
    var Locales = function(data){
        angular.copy(data, this);
    };

    Locales.query = function() {

        return $http.get('/locales').then(makeArray(Locales));
    }
    return Locales;
}).factory('Schedules', function($http){
    var Schedules = function(data){
        angular.copy(data, this);
    };

    Schedules.query = function() {
        return $http.get('/schedules').then(makeArray(Schedules));
    }
    return Schedules;
}).factory('Organizations', function($http){
    var Organizations = function(data){
        angular.copy(data, this);
    };

    Organizations.query = function() {
        return $http.get('/organizations').then(makeArray(Organizations));
    }
    return Organizations;
}).factory('Forms', function($http){
    var Forms = function(data){
        angular.copy(data, this);
    };

    Forms.query = function() {
        return $http.get('/ticket-forms').then(makeArray(Forms));
    }
    return Forms;
}).factory('Targets', function($http){
    var Targets = function(data){
        angular.copy(data, this);
    };
    Targets.query = function() {
        return $http.get('/targets').then(makeArray(Targets));
    }
    return Targets;
}).factory('RecipientAddresses', function($http){
    var RecipientAddresses = function(data){
        angular.copy(data, this);
    };

    RecipientAddresses.query = function() {
        return $http.get('/recipient-addresses').then(makeArray(RecipientAddresses));
    }
    return RecipientAddresses;
}).factory('FieldList', function($http){
    var FieldList = function(data){
        angular.copy(data, this);
    };

    FieldList.query = function() {
        return $http.get('app/resources/triggers_field.json').then(makeArray(FieldList));
    }
    return FieldList;
}).factory('OperatorList', function($http){
    var OperatorList = function(data){
        angular.copy(data, this);
    };

    OperatorList.query = function() {
        return $http.get('app/resources/triggers-operator.json').then(makeArray(OperatorList));
    }
    return OperatorList;
}).factory('FilterActionField', function($http){
    var FilterActionField = function(data){
        angular.copy(data, this);
    };

    FilterActionField.query = function() {
        return $http.get('app/resources/filter-action-field.json').then(makeArray(FilterActionField));
    }
    return FilterActionField;
}).factory('FilterConditionField', function($http){
    var FilterConditionField = function(data){
        angular.copy(data, this);
    };

    FilterConditionField.query = function() {
        return $http.get('app/resources/filter-condition-field.json').then(makeArray(FilterConditionField));
    }
    return FilterConditionField;
});*/
