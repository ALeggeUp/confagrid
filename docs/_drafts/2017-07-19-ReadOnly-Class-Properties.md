---
layout: post
title:  PowerShell Classes with readonly properties
subtitle: A short story about how to create and use them.
excerpt: "This post is about preventing direct access to class properties with C# like getter and setter methods.
It shows the syntax and usage of two different approaches. It ends with full github gist example you can work with in future."
image: /img/image/vader_cube.png
bigimg: /img/bigimg/vader_wide.png
category: PowerShell
tags: [Classes, Properties, PowerShell]
comments: true
css:
  - '/css/extend-home.css'

---
{% include about.html %}

{: .box-note}
<i class="fa fa-commenting" aria-hidden="true"></i> **Note:** If you're not familiar with Powershell classes I suggest you reading this awesome
blog article from  Michael Willis
[Powershell v5 Classes & Concepts](https://xainey.github.io/2016/powershell-classes-and-concepts/). It covers
everything your need to know about classes.

Background
==========

So lets start with some thoughts about the underlying situation. We assume there is a complex class structure and we
don't want others to mess with our data. Or maybe we need to protect our data but still need to expose it.

Therefore we can't define our class like the following example:

```powershell
Class DeathStar {
    [String]$Class = 'Space battle station'
    [Int]$Width = '160000'
    [String[]]$HyperDriveRating = @('Class 4', 'Class 20')
    $Crew = @{
        ImperialNavy = 342953
        Stormtroopers = 25984
    }
    [String]$Ready = $null

    DeathStar () {
        $this.Ready = $true
    }
}
```

With this class definition we can easily create an instance and take a look at the default displayed properties:

```console
~> $DeathStarOne = [DeathStar]::New()
~> $DeathStarOne

Class            : Space battle station
Width            : 160000
HyperDriveRating : {Class 4, Class 20}
Crew             : {Stormtroopers, ImperialNavy}
Ready            : True
```

Furthermore the rebellion could manipulate sensitive data of our death star if we would publish the death start
class like this. Let's do this with the `Ready` property and the available crew members:

```console
~> $DeathStarOne.Ready = $false
~> $DeathStartOne

Class            : Space battle station
Width            : 160000
HyperDriveRating : {Class 4, Class 20}
Crew             : {Stormtroopers, ImperialNavy}
State            : False

~> $DeathStarOne.Crew.ImperialNavy = 0
~> $DeathStarOne.Crew

Name                           Value
----                           -----
Stormtroopers                  0
ImperialNavy                   342953
```

Although Powershell doesn't have real readonly class properties, we can mimic them in two elegant ways:

* Class methods as getter and setter functions
* Script properties with getter and setter functions.

Both of them rely to the same concept of using C# like getter and setter functions. But reading and writing
the values differs.

Using class methods
===================

To isolate our sensitive data we first need to mark the sensitive properties as `hidden`. Hidden properties won't
get listed as object members unless we use the `Get-Member` function with the `-Force` switch.

Additionally I like adding an underscore _(`_`)_ as prefix to my variable names. We use the properties with
underscores alter on.

```powershell
Class DeathStar {
    [String]$Class = 'Space battle station'
    [Int]$Width = '160000'
    [String[]]$HyperDriveRating = @('Class 4', 'Class 20')
    hidden $_Crew = @{
        ImperialNavy = 342953
        Stormtroopers = 25984
    }
    hidden [String]$_Ready = $null

    DeathStar () {
        $this._Ready = $true
    }
}
```

With the customized class definition we check again our default output and members:

```console
~> $DeathStarOne = [DeathStar]::New()
~> $DeathStarOne | Format-List

Class            : Space battle station
Width            : 160000
HyperDriveRating : {Class 4, Class 20}
```

```console
~> $DeathStarOne | Get-Member

TypeName: DeathStar

Name             MemberType Definition
----             ---------- ----------
Equals           Method     bool Equals(System.Object obj)
GetHashCode      Method     int GetHashCode()
GetType          Method     type GetType()
ToString         Method     string ToString()
Class            Property   string Class {get;set;}
HyperDriveRating Property   string[] HyperDriveRating {get;set;}
Width            Property   int Width {get;set;}
```

```console
~> $DeathStarOne | Get-Member -Force

    TypeName: DeathStar

Name                 MemberType   Definition
----                 ----------   ----------
Equals               Method       bool Equals(System.Object obj)
GetHashCode          Method       int GetHashCode()
GetType              Method       type GetType()
ToString             Method       string ToString()
Class                Property     string Class {get;set;}
HyperDriveRating     Property     string[] HyperDriveRating {get;set;}
Width                Property     int Width {get;set;}
pstypenames          CodeProperty System.Collections.ObjectModel.Collection`1[[System.String, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089]] pstypenames{...
psadapted            MemberSet    psadapted {Class, Width, HyperDriveRating, get_Class, set_Class, get_Width, set_Width, get_HyperDriveRating, set_HyperDriveRating, get__Crew, set__Cre...
psbase               MemberSet    psbase {Class, Width, HyperDriveRating, get_Class, set_Class, get_Width, set_Width, get_HyperDriveRating, set_HyperDriveRating, get__Crew, set__Crew, ...
psextended           MemberSet    psextended {}
psobject             MemberSet    psobject {BaseObject, Members, Properties, Methods, ImmediateBaseObject, TypeNames, get_Members, get_Properties, get_Methods, get_ImmediateBaseObject,...
Equals               Method       bool Equals(System.Object obj)
GetHashCode          Method       int GetHashCode()
GetType              Method       type GetType()
get_Class            Method       string get_Class()
get_HyperDriveRating Method       string[] get_HyperDriveRating()
get_Width            Method       int get_Width()
get__Crew            Method       System.Object get__Crew()
get__Ready           Method       string get__Ready()
set_Class            Method       void set_Class(string )
set_HyperDriveRating Method       void set_HyperDriveRating(string[] )
set_Width            Method       void set_Width(int )
set__Crew            Method       void set__Crew(System.Object )
set__Ready           Method       void set__Ready(string )
ToString             Method       string ToString()
Class                Property     string Class {get;set;}
HyperDriveRating     Property     string[] HyperDriveRating {get;set;}
Width                Property     int Width {get;set;}
_Crew                Property     System.Object _Crew {get;set;}
_Ready               Property     string _Ready {get;set;}
```

Next step is to add individual getter and setter methods to access the hidden variable. In our scenario we don't
need the setter part, because that's the action we want to restrict. But to show you the syntax I added the `SetCrew`
method.

```powershell
Class DeathStar {
    [String]$Class = 'Space battle station'
    [Int]$Width = '160000'
    [String[]]$HyperDriveRating = @('Class 4', 'Class 20')
    hidden $_Crew = @{
        ImperialNavy = 342953
        Stormtroopers = 25984
    }
    hidden [string]$_Ready = $null

    DeathStar () {
        $this._Ready = $true
    }

    [string] GetReady () {
        return $this._Ready
    }

    [string] GetCrew ([string]$Type) {
        return $this._Crew.$Type
    }

    SetCrew ([string]$Type, [Int]$Value) {
        Write-Warning 'Apology accepted, Captain Needa.'
        $this._Crew.$Type = $Value
    }
}
```

If you take a look at the members again, you get now a list off all _public_ properties and methods we've created.

```console
~> $DeathStarOne | Get-Member

   TypeName: DeathStar

Name             MemberType Definition
----             ---------- ----------
Equals           Method     bool Equals(System.Object obj)
GetCrew          Method     string GetCrew(string Type)
GetHashCode      Method     int GetHashCode()
GetReady         Method     string GetReady()
GetType          Method     type GetType()
SetCrew          Method     void SetCrew(string Type, int Value)
ToString         Method     string ToString()
Class            Property   string Class {get;set;}
HyperDriveRating Property   string[] HyperDriveRating {get;set;}
Width            Property   int Width {get;set;}
```

That's it. Now you can access to your data while using the methods `GetCrew()`, `SetCrew()` and `GetReady()`. Keep
in mind you can always isolate properties with methods like this as an kind of interface.

{: .box-warning}
<i class="fa fa-bolt" aria-hidden="true"></i> **WARNING:** If you work with complex classes containing a huge amount of public and hidden
properties, this could get a problem. Because the way how you get and set values depends now on either it's a
property or methods. So you could loose track of all members and how to work with them.

Using Script Properties
=======================

Script properties are special class members which executes `get` or `set` methods depending on the action.

You can add script properties to the well known _PSCustomObject_ or classes. Therefore you can use the
`Add-Member` function as well.

Here is a simple example with a _PSCustomObject_ to show the syntax:

```powershell
$MyXWing = [PSCustomObject]@{
    'Model' = 'T-65'
    'Class' = 'Starfighter'
    'Crew' = @{
        'Pilot' = 1
        'Astromech Droid' = 1
    }
    '_Ready' = $true
}

$MyXWing | Add-Member -Name 'Ready' -MemberType ScriptProperty -Value {
    # Getter
    return $this._Ready
} -SecondValue {
    # Setter
    Write-Warning 'This is a readonly property!'
}
```

```console
~> $MyXWing | Format-List

Model  : T-65
Class  : Starfighter
Crew   : {Pilot, Astromech Droid}
_Ready : True
Ready  : True

~> $MyXWing.Ready
True

~> $MyXWing.Ready = $false
WARNING: This is a readonly property!
```

If we want to use the script property in a class we have to create it in the class constructor.
The class constructor knows the keyword `$this` which refers to te current object.

```powershell
Class DeathStar {
    [String]$Class = 'Space battle station'
    [Int]$Width = '160000'
    [String[]]$HyperDriveRating = @('Class 4', 'Class 20')
    $Crew = @{
        ImperialNavy = 342953
        Stormtroopers = 25984
    }
    hidden [String]$_Ready = $null

    DeathStar () {
        $this._Ready = $true
        $this | Add-Member -MemberType ScriptProperty -Name 'Ready' -Value {
            # Getter
            return $this._Ready
        } -SecondValue {
            # Setter
            Write-Warning 'This is a readonly property!'
        }
    }
}
```

```console
~> $DeathStarOne = [DeathStar]::New()
~> $DeathStarOne

Ready            : True
Class            : Space battle station
Width            : 160000
HyperDriveRating : {Class 4, Class 20}
Crew             : {Stormtroopers, ImperialNavy}

~> $DeathStarOne | Get-Member | Format-Table

   TypeName: DeathStar

Name             MemberType     Definition
----             ----------     ----------
Equals           Method         bool Equals(System.Object obj)
GetHashCode      Method         int GetHashCode()
GetType          Method         type GetType()
ToString         Method         string ToString()
Class            Property       string Class {get;set;}
Crew             Property       System.Object Crew {get;set;}
HyperDriveRating Property       string[] HyperDriveRating {get;set;}
Width            Property       int Width {get;set;}
Ready            ScriptProperty System.Object Ready {get=...
```

And that's exactly what we wanted. We have hidden the the property `_Ready` and exposed a script property called
`Ready`. We can now get or set the values of to the script property like we would do it with a _normal_ property.

Final Conclusion
================

I personally like using _script properties_. But I take is on step further and create all public properties with a
separate method:

{% gist OCram85/d673764614438493afaa5cd413999436 AddPublicMember.ps1 %}

This avoids making errors if I work with multiple constructors. Without a method like `AddPublicMember` you are
forced to define each public property in every constructor method.

All you have to do is to add the `AddPublicMember`method to your class definition and call it in every constructor.

Finally our death start class looks like this:

{% gist OCram85/d673764614438493afaa5cd413999436 DeathStarClass.ps1 %}

So what do you think? - Feel free to discuss this in the comment section down below.
