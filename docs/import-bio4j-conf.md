# Bio4j data import

## EC2 instance configuration

We use `i2.8xlarge` instances; you can get their details from http://www.ec2instances.info/?selected=i2.8xlarge

### Instance store configuration

These instances have **8** devices with approx **800GB** each, for a total of **6.4TB**. We use plain `RAID0`. AWS already does over-provisioning for you, and the actual size of the devices is **745.2GB** so we get **5.5TB**.

``` bash
#!/bin/sh
# use whatever as name
RAID_NAME=tmpdata
mdadm --create --verbose /dev/md0 --level=0 --name=$RAID_NAME --raid-devices=8 xvdb xvdc xvdd xvde xvdf xvdg xvdh xvdi
# create a FS
mkfs.ext4 -L $RAID_NAME /dev/md0
mkdir -p /mnt/$RAID_NAME
mount LABEL=$RAID_NAME /mnt/$RAID_NAME
```

References for RAID0 and related matters

- http://docs.aws.amazon.com/AWSEC2/latest/UserGuide/raid-config.html#linux-raid
- https://forums.aws.amazon.com/thread.jspa?messageID=194926&#194926
- https://wiki.archlinux.org/index.php/RAID
- https://raid.wiki.kernel.org/index.php/RAID_setup#RAID-0
- http://docs.aws.amazon.com/AWSEC2/latest/UserGuide/i2-instances.html
- https://sysadmincasts.com/episodes/27-lvm-linear-vs-striped-logical-volumes

## AWS costs

### EC2

`i2.8xlarge` spot price is around `0.7$/h`, which means ~`15$/d`.

## TODO

- [ ] remove arguments from import programs
- [ ] remove uniprotxml conf xml
- [ ] keep original file names in the raw bucket
- [ ] factor stuff into methods
- [ ] titan properties should be part of the code
