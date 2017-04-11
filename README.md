# Peximet
Akka cluster sample on Kubernetes


## Installation
```sh
# To deploy the seed nodes:
kubectl apply -f kubernetes/peximet-seed.yaml
# To deploy the worker nodes:
kubectll apply -f kubernetes/peximet-workers.yaml

#Option 2: to deploy everything
kubectl apply -f kubernetes/
```

## Usage
```sh
# Scale seed nodes:
kubectl scale statefulset peximet-seed --replicas=5

#Scale worker nodes:
kubectl scale deployment peximet-workers --replicas=7

#See logs:
kubectl logs -f peximet-seed-4
```

## TODO
 - Create fatjar using sbt
 - Add tests
 - ~~Implement example actor model scenario~~
