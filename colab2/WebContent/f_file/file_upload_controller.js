app.controller('FileUploadController', ['$scope', '$q', 'fileUpload', function($scope, $q, fileUpload){
 
	console.log('aaaaa');
	var self=this;
	self.dataUpload = true;
	self.errVisibility = false;
	
	self.uploadFile = function(){
	 alert('ssss');
	 var file = $scope.myFile;
	 console.log('file is ' );
	 console.dir(file);

var uploadUrl = "http://localhost:8080/collaboration/fileUpload/";
 fileUpload.uploadFileToUrl(file, uploadUrl).then(function(result){
	 self.errors = fileUpload.getResponse();
 console.log($scope.errors);
 self.errVisibility = true;
 }, function(error) {
 alert('error');
 })

};
 }]);