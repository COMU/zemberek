#!/bin/sh
source runbase.sh
java -DUSE_DBUS_SYSTEM_CONNECTION=no \
	-cp zemberek-server-${surum}.jar \
	-Djava.library.path=lib/native-$(uname -m) net.zemberekserver.client.DBusClient
