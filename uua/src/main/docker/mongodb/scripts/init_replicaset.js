var status = rs.status();
if(status.errmsg === 'no replset config has been received') {
    rs.initiate();
}
for (var i = 1; i <= param; i++) {
    if(i!==1)
        rs.add(folder+"_uua-mongodb-node_" + i + ":27018");
}
cfg = rs.conf();
cfg.members[0].host = folder+"_uua-mongodb-node_1:27018";
rs.reconfig(cfg);
