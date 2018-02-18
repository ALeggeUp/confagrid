---
layout: post

title:  "Counterfeit Prolific Chip"
subtitle: ""
image: "/img/posts/06/Counterfeit-Forgery.jpg"
show-avatar: true
category: ["Conversational"]
tags: ["Conversational", "Troubleshooting"]
comments: true
social-links: true

---
As with many of the electronic things that I’ve purchased on eBay, this item came from China in five to eight weeks and was unbelievably inexpensive compared to anywhere else.


![alt text](/img/posts/06/img_20160212_012708.jpg " ")


I have no idea how it is possible to manufacture, assemble and ship anything 10,000KM for under two dollars, so when I receive something that doesn’t look like a commercial grade product, I’m not really shocked or disappointed.

Right away I noticed that a few components weren’t aligned quite right, but that didn’t really bother me since there didn’t appear to be any shorts and everything seemed to match up with the silkscreen, etc.

When I plugged this device into a Windows computer with drivers installed, I received an error “This Device cannot start (Code 10)”.  It didn’t take very long to find several others online with the same issue and a workaround to get it working.  The workaround for me was to simply use an older driver, since the only reason the latest one wasn’t working was because Prolific had found a way to detect the counterfeits and make it so they wouldn’t work.

I don’t have any problem with Prolific excluding counterfeit devices from their driver, they handled it much better than FTDI which spawned the infamous #FTDIGate uproar when they released drivers that bricked counterfeit devices.

The part that I don’t understand is why they go through the trouble of trying to counterfeit the chip in the first place.  When I searched on eBay I was looking for the cheapest USB-to-Serial device I could find and all I needed it to do was allow me to send serial communications from a computer that didn’t have a serial port and I wouldn’t have really cared what chip was doing the work.

I wish they would just be more open about it.  If they said that they had a custom chip that allowed for USB-to-Serial communications for a fraction of the cost of a comparable Prolific based device, I’d have still purchased the same device because I would have felt it was worth the risk.

In the end, I’m satisfied with how the device performs.  Once I got it working with the older driver I haven’t had any problems with it, so no harm other than a little head scratching and finding older drivers that aren’t supposed to be published anywhere.
