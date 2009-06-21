require 'java'
include Java

server_jar = "lib/yr-api-jar-with-dependencies" || ENV['SERVER_JAR']

require server_jar
include_class "no.yr.LocationApi"
include_class "no.yr.svg.TimeSeriesRendrer"

class SvgServlet 
  def do_GET(req, res)
    lat = nil
    lon = nil
    height = 0
    begin
      lat, lon, height = process(req)
    rescue Exception => e
      raise e
    end

    begin
      api_result = LocationApi.new()
      api_result.setLatitude(lat.to_f)
      api_result.setLongitude(lon.to_f)
      api_result.setMoh(height.to_i)
    rescue Exception => e
      raise "initalisation of location api failed" +  e.message
    end
    
    timeseries = api_result.fetchTimeseriesForHours(24.to_i)
    
    res["status"] = 200
    res["body"] = TimeSeriesRendrer.new(timeseries).getXml
    res['Content-Type'] = " image/svg+xml"
    res
  end
  
  
  
  def process(request)
    raise "lat and lon must be set" unless not request['lat'] == nil and not request['lon'] == nil
    
    if request['height'] == nil
      return request['lat'], request['lon'], 0
    else
      return request['lat'], request['lon'], request['height']
    end
        
  end
end
