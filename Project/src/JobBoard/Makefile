GLASSFISH=~/Programs/glassfish-3.1.2.2
BROWSER=google-chrome
URL=http://localhost:8080/JobBoard/

.PHONY: browser start stop jlint docs
all: start
	ant -f /home/dan/Code/com3014_group_cw/src/JobBoard/build.xml -DforceRedeploy=false -Ddirectory.deployment.supported=true -Dnb.wait.for.caches=true run-deploy

browser:
	$(BROWSER) $(URL) &

start:
	$(GLASSFISH)/glassfish/bin/startserv > glassfish.log 2>&1 &
stop:
	$(GLASSFISH)/glassfish/bin/stopserv >> glassfish.log 2>&1

jlint:
	# Jlint check
	jlint +all build/web/WEB-INF/classes/

docs:
	doxygen doxygen.conf
