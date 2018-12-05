$patternToReplace = "tags = {.*}"
$replaceWithString = "tags = {`"@kalibrate`",`"@pullrequest`",`"~@ignore`",`"~@wip`"}"

# need to do things this way because of the BOM if I do a simple write-output
$fileName = "src\test\java\com\runners\Kalibrate_Runner_Test.java"
$content = Get-Content $fileName | % { $_ -replace $patternToReplace, $replaceWithString } 
[System.IO.File]::WriteAllLines(($fileName | Resolve-Path), $content)
