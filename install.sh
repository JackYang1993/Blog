#!/bin/bash


# https://api.github.com/repos/GuoJiafeng/ProblemRepository/contents

wget -c "http://files.git.oschina.net/group1/M00/07/63/PaAvDFy5ceKASQZAAA5O-Gs--YQ242.rpm?token=cd490e7f57a2dbea98ea04dc4a884499&ts=1555657274&attname=nginx-1.12.2-1.el6.ngx.x86_64.rpm&disposition=attachment"  -O nginx-1.12.2-1.el6.ngx.x86_64.rpm


rpm -ivh nginx-1.12.2-1.el6.ngx.x86_64.rpm

nginx

rm -rf /usr/share/nginx/html/*


#yum remove git*
#
#wget -c "http://files.git.oschina.net/group1/M00/07/63/PaAvDFy5cemAdOGyAH6LPKZEAXw4781.gz?token=9d65cf0f4febc5979b3603b09c3052d6&ts=1555657269&attname=git-2.21.0.tar.gz&disposition=attachment" -O git-2.21.0.tar.gz
#
#
#tar -zxvf git-2.21.0.tar.gz
#
#cd git-2.21.0
#
#yum install curl-devel expat-devel gettext-devel openssl-devel zlib-devel gcc perl-ExtUtils-Embed
#
#make prefix=/usr/local/git install
#
#/usr/local/git/bin/git --version










