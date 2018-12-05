param (
    [parameter(mandatory=$true)] [string] $URL,
    [string] $USER = "autoone",
    [string] $PWD = "letmein123"
)

[string] $hash = $(git rev-parse --short HEAD)

docker run --rm -it --name uat `
-e DOCKER_URL=$URL `
-e DOCKER_RETAIL_PRICING_ANALYST_USERNAME_1=$USER `
-e DOCKER_RETAIL_PRICING_ANALYST_PASSWORD=$PWD `
-v log_output:c:\output `
-m 2g `
apitest
