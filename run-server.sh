#! /usr/bin/expect -f
cd /home/hdbadm/workspace/cmu-sensor-network
eval spawn {*}"/home/hdbadm/src/play-2.1.3/play"
set prompt "CMUSensorNetwork"
expect "$prompt"
#interact -o -nobuffer -re $prompt return
send "run 8080\r"
interact
