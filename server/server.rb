#!/usr/bin/jruby1.0
require 'webrick'
include WEBrick

require 'svg_servlet.rb'

server_port = ENV['PORT'].to_i || 2000

s = HTTPServer.new( :Port => server_port, :BindAddress  => "0.0.0.0" )

# HTTPServer#mount(path, servletclass)
#   When a request referring "/hello" is received,
#   the HTTPServer get an instance of servletclass
#   and then call a method named do_"a HTTP method".


s.mount("/", SvgServlet)


trap("INT"){ s.shutdown }
s.start

