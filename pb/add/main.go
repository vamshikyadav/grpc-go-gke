package main

import (
	"fmt"
        "golang.org/x/net/context"
	"google.golang.org/grpc"
	"google.golang.org/grpc/reflection"
	"log"
	"net"
    
        // replace this with your own project
	"github.com/shuza/kubernetes-go-grpc/pd"
)