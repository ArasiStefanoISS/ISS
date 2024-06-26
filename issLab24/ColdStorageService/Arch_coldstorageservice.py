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
with Diagram('coldstorageserviceArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxcss', graph_attr=nodeattr):
          soldstorageservice=Custom('soldstorageservice','./qakicons/symActorSmall.png')
          drivermock=Custom('drivermock','./qakicons/symActorSmall.png')
     drivermock >> Edge(color='magenta', style='solid', decorate='true', label='<store<font color="darkgreen"> store_accepted store_rejected</font> &nbsp; >',  fontcolor='magenta') >> soldstorageservice
diag
