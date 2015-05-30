/**
 * Created by Aleksandar on 17/5/2015.
 */
FirstApp.factory('Attachment', ['$resource', function ($resource) {
  return $resource('/object/:id', {}, {
    'getAttachmentsByObjectId': {
      method: 'GET',
      isArray: true,
      url: "/data/rest/attachments/:bean/:id"
    },
    'deleteAttachment': {
      method: 'DELETE',
      url: "/data/rest/attachments/:id"
    }
  });
}]);
