Function Invoke-Install () {
    [CmdletBinding()]
    Param()
    Write-Verbose 'Installing NuGet...'
    Install-PackageProvider -Name NuGet -MinimumVersion '2.8.5.201' -Force
    Import-PackageProvider NuGet -MinimumVersion '2.8.5.201' -Force

    Write-Verbose 'Installing required Powershell modules...'
    Install-Module -Name 'posh-git' -Scope CurrentUser -Force -SkipPublisherCheck -AllowClobber
    Update-Module 'posh-git'

    Write-Verbose 'Setting up latest ruby version...'
    $env:Path = $env:Path.Replace('Ruby193', 'Ruby24-x64')
    ruby --version
    gem -v
    bundle -v

    Write-Verbose 'Installing bundler and required packages...'
    # Disable bundles itself, because it should already exist.
    #gem update bundler
    bundle install
    # try to fix AppVeyor build error
    #gem install i18n-0.8.6
}

Function Invoke-GitConfig () {
    [CmdletBinding()]
    Param()
    Write-Verbose 'Setting up git...'
    git config --local user.name "OCram85"
    git config --local user.email "marco.blessing@googlemail.com"
    git config --global credential.helper store
    git config --global core.autocrlf false
    Add-Content "$env:USERPROFILE\.git-credentials" "https://$($env:access_token):x-oauth-basic@github.com`n"
    If ($DebugPreference -ne 'SilentlyContinue') {
        Write-Host 'Local git config:' -ForegroundColor Green
        git config --list --local
        Write-Host 'global git config:' -ForegroundColor Green
        git config --list --global
        Write-Host 'system git config:' -ForegroundColor Green
        git config --list --system
    }

}

Function Invoke-BeforeBuild () {
    [CmdletBinding()]
    Param()
    Write-Verbose "Setting up git branch..."
    If ($DebugPreference -ne 'SilentlyContinue') {
        Write-Host 'Local branches:' -ForegroundColor Green
        git branch -v
        Write-Host 'Remote branches:' -ForegroundColor Green
        git branch -rv
        Write-Host 'Show remote' -ForegroundColor Green
        git remote -v
        Write-Host 'Show origin details' -ForegroundColor Green
        git remote show origin
    }
    git checkout master
    git reset origin/$($Env:APPVEYOR_REPO_BRANCH) --hard
    Write-Host -Object ("Git branch switched to: {0}" -f (Get-GitBranch)) -f Green
}

Function Invoke-BumpBuildNumber () {
    [CmdletBinding()]
    Param()
    $Footer = Get-Content -Path '.\_includes\footer.html' -Encoding UTF8
    $Footer = $Footer.Replace('[[BUILD_NUMBER]]', $Env:APPVEYOR_BUILD_NUMBER)
    Set-Content -Path '.\_includes\footer.html' -Encoding UTF8 -Value $Footer
}
Function Invoke-BuildScript () {
    [CmdletBinding()]
    Param()
    Write-Verbose 'Building jekyll pages...'
    bundle exec jekyll build
    Write-Verbose 'done.'
}

Function Invoke-Deploy () {
    [CmdletBinding()]
    Param()
    If ($Env:APPVEYOR_REPO_BRANCH -match 'jekyll') {
        Write-Verbose 'Push changes to origin...'
        Get-ChildItem | Where-Object { $_.Name -notmatch "^(.git|_site|CNAME|.gitignore)$"} | Remove-Item -Recurse -Confirm:$False -Force
        Move-Item ".\_site\*" -Destination '.\'
        Remove-Item '.\_site' -Recurse -Force
        git add -A
        git commit -m 'rebuild jekyll sites'
        git push --force
    }
    Else {
        Write-Warning ('Deployment is disabled for the current branch: {0}. The deployment works only with <jekyll>.' -f $Env:APPVEYOR_REPO_BRANCH)
    }
}
