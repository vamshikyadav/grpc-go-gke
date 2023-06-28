gcloud artifacts repositories create hello-repo --repository-format=docker  --location=us-central1 --description="Docker repository"
gcloud builds submit --tag us-central1-docker.pkg.dev/arched-light-391223/hello-repo/helloworld-gke .



// Below is the grpc service

https://medium.com/hackernoon/how-to-develop-go-grpc-microservices-and-deploy-in-kubernates-5eace0425bf8

usign protobuf:
protoc -I . --go-grpc_out=. ./*.proto


istio ingress:

google search: port: grpc istio gke github

https://github.com/GoogleCloudPlatform/istio-samples/tree/master/sample-apps/grpc-greeter-go

https://auth0.com/blog/securing-kubernetes-clusters-with-istio-and-auth0/

https://github.com/istio/istio/issues/27320
https://github.com/istio/istio/issues/7909

