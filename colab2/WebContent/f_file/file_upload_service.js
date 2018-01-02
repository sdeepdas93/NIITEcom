app.service('fileUpload', ['$q','$http', function ($q,$http) {
 var deffered = $q.defer();
 var responseData;
 this.uploadFileToUrl = function(file, uploadUrl){
 var fd = new FormData();
 fd.append('file', file);
 return $http.post(uploadUrl, fd, {
 transformRequest: angular.identity,
 headers: { 'Content-Type' : undefined}
 })
 .success(function(response){

/* $scope.errors = response.data.value; */
 console.log(response);
 responseData = response;
 deffered.resolve(response);
 return deffered.promise;
 })
 .error(function(error){
 deffered.reject(error);
 return deffered.promise;
 });

}

this.getResponse = function() {
 return responseData;
 }

}]);