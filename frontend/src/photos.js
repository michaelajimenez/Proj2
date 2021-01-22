const albumBucketName = "earthlings-bucket";
const bucketRegion = "us-east-1";
const IdentityPoolId = "us-east-1:91d14775-d05a-4cf3-93d0-2388bdd5b88f";
AWS.config.update({
  region: bucketRegion,
  credentials: new AWS.CognitoIdentityCredentials({
    IdentityPoolId: IdentityPoolId,
  }),
});

const s3 = new AWS.S3({
  apiVersion: "2006-03-01",
  params: { Bucket: albumBucketName },
});

function addPhoto(albumName, file) {
  let photokey;
  const fileName = file.name;
  const albumPhotosKey = encodeURIComponent(albumName) + "/";
  const photoKey = albumPhotosKey + fileName;

  // Use S3 ManagedUpload class as it supports multipart uploads
  const upload = new AWS.S3.ManagedUpload({
    params: {
      Bucket: albumBucketName,
      Key: photoKey,
      Body: file,
    },
  });

  const promise = upload.promise();

  promise.then(function (data) {
    return (photokey = data.Location);
  });

  return photoKey;
}

export default addPhoto;
