### conda install diagrams
from diagrams import Cluster, Diagram, Edge
from diagrams.custom import Custom
import os
os.environ['PATH'] += os.pathsep + 'C:/Program Files/Graphviz/bin/'

graphattr = {     #https://www.graphviz.org/doc/info/attrs.html
    'fontsize': '22',
}

nodeattr = {   
    'fontsize': '22',
    'bgcolor': 'lightyellow'
}

eventedgeattr = {
    'color': 'red',
    'style': 'dotted'
}
evattr = {
    'color': 'darkgreen',
    'style': 'dotted'
}
with Diagram('wdArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxstation', graph_attr=nodeattr):
          station=Custom('station(ext)','./qakicons/externalQActor.png')
     with Cluster('ctxrasp', graph_attr=nodeattr):
          rasp=Custom('rasp','./qakicons/symActorSmall.png')
          blueled=Custom('blueled','./qakicons/symActorSmall.png')
          sonar=Custom('sonar','./qakicons/symActorSmall.png')
          camera=Custom('camera','./qakicons/symActorSmall.png')
     with Cluster('ctximg', graph_attr=nodeattr):
          img=Custom('img(ext)','./qakicons/externalQActor.png')
     rasp >> Edge(color='magenta', style='solid', decorate='true', label='<foto<font color="darkgreen"> rplFoto</font> &nbsp; >',  fontcolor='magenta') >> camera
     rasp >> Edge(color='magenta', style='solid', decorate='true', label='<recogn<font color="darkgreen"> rplRecogn</font> &nbsp; >',  fontcolor='magenta') >> img
     rasp >> Edge(color='blue', style='solid',  decorate='true', label='<off &nbsp; on &nbsp; stop &nbsp; >',  fontcolor='blue') >> blueled
     rasp >> Edge(color='blue', style='solid',  decorate='true', label='<start &nbsp; stop &nbsp; >',  fontcolor='blue') >> sonar
     rasp >> Edge(color='blue', style='solid',  decorate='true', label='<stop &nbsp; >',  fontcolor='blue') >> camera
     sonar >> Edge(color='blue', style='solid',  decorate='true', label='<sonar &nbsp; >',  fontcolor='blue') >> rasp
     rasp >> Edge(color='blue', style='solid',  decorate='true', label='<on &nbsp; off &nbsp; >',  fontcolor='blue') >> station
diag
