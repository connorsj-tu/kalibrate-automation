# get git short hash
[string] $hash = $(git rev-parse --short HEAD)
write-output "Building Kalibrate UI-Automation version $hash..."

docker build -t kalibrate/ui-auto .
docker tag kalibrate/ui-auto kalibrate/ui-auto:$hash
